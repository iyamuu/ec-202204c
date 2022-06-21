package com.example.ecommerce_c.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_c.domain.Topping;

@Repository
public class ToppingRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static RowMapper<Topping> ROW_MAPPER = (rs, i) -> {
		Topping topping = new Topping();
		topping.setId(rs.getInt("id"));
		topping.setName(rs.getString("name"));
		topping.setPriceM(rs.getInt("price_m"));
		topping.setPriceL(rs.getInt("price_l"));
		return topping;
	};
	
	public List<Topping> getTopping() {
		String sql = 
				"select id, name, price_m, price_l from toppings";
		List<Topping> toppingList = template.query(sql, ROW_MAPPER);
		return toppingList;
	}
}
