package com.uol.compass.pb.ecommerce.enums;

public enum StatusVenda {
	AGUARDANDO_PAGAMENTO("Aguardando Pagamento"),
	PAGAMENTO_EFETIVADO("Pagamento em separaçao"),
	PEDIDO_EM_SEPERACAO("Pedido separado"),
	PEDIDO_EM_TRASITO("Pedido em transito"),
	ENTREGUE("Entregue");
	
	private final String descricao;
	
	private StatusVenda(String descricao) {
		this.descricao = descricao;
	}	
	
	public String obterDescricao() {
		  return descricao;
	}
}
