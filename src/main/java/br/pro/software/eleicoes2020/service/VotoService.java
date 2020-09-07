package br.pro.software.eleicoes2020.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfWriter;

import br.pro.software.eleicoes2020.model.Pessoa;
import br.pro.software.eleicoes2020.model.Voto;
import br.pro.software.eleicoes2020.repository.VotoRepository;

@Service
public class VotoService {
	private final Font fonteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
	private final Font fonteTexto = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	@Autowired
	VotoRepository votoRepo;

	public boolean salvar(Voto voto) {
		votoRepo.save(voto);
		return true;
	}

	public boolean jaVotou(Pessoa pessoa) {
		return votoRepo.existsByPessoaId(pessoa.getId());
	}

	public boolean podeVotar(Pessoa pessoa) {
		return pessoa.getApto() && !votoRepo.existsByPessoaId(pessoa.getId());
	}

	public ByteArrayInputStream gerarPdf(Pessoa pessoa) {
		List<Voto> votos = votoRepo.findAllByPessoaId(pessoa.getId());
		Optional<Voto> voto = votos.stream().sorted((v1, v2) -> v1.getCriado().compareTo(v2.getCriado())).findFirst();
		Document document = new Document();
		document.setPageSize(PageSize.A3);
		document.addTitle("Comprovante Votação");
		document.addSubject("guarde seu comprovante");
		document.addKeywords("comprovante, votação");
		document.addAuthor("Anderson Sanches");
		document.addCreator("software.pro.br");
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			Path path = Paths.get(ClassLoader.getSystemResource("public/images/logoiba.png").toURI());
			Image logo = Image.getInstance(path.toAbsolutePath().toString());

			Paragraph conteudo = new Paragraph();
			conteudo.add(new Paragraph(" "));
			conteudo.add(new Paragraph("Comprovante de Votação", fonteTitulo));
			conteudo.add(new Paragraph(" "));
			conteudo.add(new Paragraph(pessoa.getEleicao().getNome(), fonteTitulo));
			conteudo.add(new Paragraph(" "));
			adicionarCabecalho(conteudo, pessoa.getEleicao().getCabecalho());
			conteudo.add(new Paragraph(" "));
			conteudo.add(new Paragraph("Documento: " + pessoa.getDocumento(), fonteTexto));
			conteudo.add(new Paragraph("Nome: " + pessoa.getNome(), fonteTexto));
			conteudo.add(new Paragraph(" "));
			conteudo.add(new Paragraph(pessoa.getEleicao().getRodape(), fonteTexto));

			//			PdfPTable table = new PdfPTable(3);
			//			table.setWidthPercentage(60);
			//			table.setWidths(new int[]{1, 3, 3});
			//
			//			PdfPCell hcell;
			//			hcell = new PdfPCell(new Phrase("Id", headFont));
			//			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			//			table.addCell(hcell);
			//
			//			hcell = new PdfPCell(new Phrase("Name", headFont));
			//			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			//			table.addCell(hcell);
			//
			//			hcell = new PdfPCell(new Phrase("Population", headFont));
			//			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			//			table.addCell(hcell);

			//			for (City city : cities) {

			//			PdfPCell cell;
			//
			//			cell = new PdfPCell(new Phrase(pessoa.getId().toString()));
			//			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			//			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			//			table.addCell(cell);
			//
			//			cell = new PdfPCell(new Phrase(pessoa.getNome()));
			//			cell.setPaddingLeft(5);
			//			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			//			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			//			table.addCell(cell);
			//
			//			cell = new PdfPCell(new Phrase(String.valueOf(pessoa.getDocumento())));
			//			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			//			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			//			cell.setPaddingRight(5);
			//			table.addCell(cell);
			//			}

			PdfWriter.getInstance(document, out);
			document.open();
			document.add(logo);
			document.add(conteudo);
			if (voto.isPresent()) {
				BarcodeQRCode barcodeQRCode = new BarcodeQRCode("https://eleicoesiba2020.com.br/valid/" + voto.get().getId() +
						"/" + voto.get().getCriado().toEpochSecond(), 1000, 1000, null);
				Image codeQrImage = barcodeQRCode.getImage();
				codeQrImage.scaleAbsolute(100, 100);
				document.add(codeQrImage);
			}
			document.close();
		} catch (DocumentException | IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		return new ByteArrayInputStream(out.toByteArray());
	}

	private void adicionarCabecalho(Paragraph conteudo, String cabecalho) {
		String[] linhas = cabecalho.split("\\r?\\n");
		for (int i = 0; i < linhas.length; i++) {
			conteudo.add(new Paragraph(linhas[i], fonteTitulo));
		}
	}

	public boolean validar(long id, long zdt) {
		return votoRepo.findById(id).orElse(new Voto()).getCriado().toEpochSecond() == zdt;
	}
}
