package br.edu.ufape.sgu_extra_sisu_service.controller.response;

public record PagamentoResponse(
        String status,
        String mensagem,
        String codigoBarra, // Opcional para boleto
        String urlPdf
) {
    public PagamentoResponse(String status, String mensagem) {
        this(status, mensagem, null, null);
    }
}
