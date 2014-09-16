package br.org.financas.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 * Entidade que representar uma transação, que pode ser, do tipo entrada, que soma, ou saida
 * que subtrai do valor total;
 * @author ccruz
 *
 */
@Entity
public class Transacao implements Serializable {
	
	/**
	 * Serial version?
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "TABLE_GENERATOR", table = "SEQUENCE_TABLE", pkColumnName = "TABLE_NAME", pkColumnValue = "TRANSACAO_ID", valueColumnName = "TABLE_VALUE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GENERATOR")
	private long id;
	
	/**
	 * Valor da transação
	 */
	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal valor;
	
	/**
	 * Tipo = Entrada ou saida
	 * Usar enum
	 */
	@Enumerated(EnumType.STRING)
	private TipoTransacao.Tipo tipo;
	
	/**
	 * Descri��o da transa��o
	 */
	private String descricao;
	
	/**
	 * Data da transação
	 */
	private Date data;	
	
	/**
	 * Verifica antes se o valor for negativo, retorna positivo 
	 * @return valor
	 */
	public BigDecimal getValor() {
		if (this.valor == null)
			return valor;
		//Se for um número negativo, transforma ele em positivo
		//deixa negativo apenas no banco, é mais facil pra fazer calculos no select.
		if (this.valor.compareTo(BigDecimal.ZERO) == -1)
			return this.valor.negate();
		 else 
			 return this.valor;
			
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoTransacao.Tipo getTipo() {
		return tipo;
	}

	public void setTipo(TipoTransacao.Tipo tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}