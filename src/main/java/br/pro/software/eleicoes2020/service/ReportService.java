package br.pro.software.eleicoes2020.service;

import br.pro.software.eleicoes2020.model.Eleicao;
import br.pro.software.eleicoes2020.repository.VotoRepository;
import br.pro.software.eleicoes2020.repository.projection.ResultadoProjection;
import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.stream.Stream;

@Service
public class ReportService {

    @Autowired
    VotoRepository votoRepository;

    @Transactional()
    public void resultado(Writer writer, Eleicao eleicao) throws IOException {
        String[] cabecalho = { "Candidato", "Votos" };
        try (
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.builder().setHeader(cabecalho).build());
            Stream<ResultadoProjection> resultados = votoRepository.resultado(eleicao.getId());
        ) {
            resultados.forEach(r -> {
                try {
                    csvPrinter.printRecord(
                            r.getNome(),
                            r.getVotos()
                    );
                } catch (IOException e) {
                    throw new RuntimeException("Erro ao escrever registro no CSV", e);
                }
            });
            csvPrinter.flush();
        }
    }
}
