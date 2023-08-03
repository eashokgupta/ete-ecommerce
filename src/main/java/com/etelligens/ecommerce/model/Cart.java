package com.etelligens.ecommerce.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.etelligens.ecommerce.auth.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Cart")
public class Cart implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 7556208287679752588L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_email", nullable = false, referencedColumnName = "email")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private User user;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id",unique = true, referencedColumnName = "id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Product productId;
	private Integer quantity;
	private Integer price;
	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}