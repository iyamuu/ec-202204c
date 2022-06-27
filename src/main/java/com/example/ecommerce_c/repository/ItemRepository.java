package com.example.ecommerce_c.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_c.domain.GiftInformation;
import com.example.ecommerce_c.domain.Item;

/**
 * 商品情報を扱うリポジトリ.
 * 
 * @author hvthinh
 *
 */
@Repository
public class ItemRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 商品情報を取得するローマッパー.
	 */
	private static RowMapper<Item> ROW_MAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPriceM(rs.getInt("price_m"));
		item.setPriceL(rs.getInt("price_l"));
		item.setImagePath(rs.getString("image_path"));
		item.setTargetAge(rs.getInt("target_age"));
		item.setGender(rs.getString("gender"));
		item.setDeleted(rs.getBoolean("deleted"));
		return item;
	};

	/**
	 * 絞り込みを適応して検索を行う.
	 * 
	 * @param from スタート
	 * @param to   ゴール
	 * @return アイテム
	 */
	public List<Item> findPages(Integer from, Integer to, GiftInformation giftInformation) {
		
		String sql;
		SqlParameterSource param;
		
		if(giftInformation.getGender() == null) {
			sql = "select id, name, description, price_m, price_l, image_path, target_age, gender, deleted from items "
					+ "Where target_age Between :lowerAge And :upperAge And price_m Between :lowerBudget And :upperBudget "
					+ "order by id"/* offset :from rows fetch next :to rows only;"*/;
			param = new MapSqlParameterSource().addValue("lowerAge", giftInformation.getLowerAge()).addValue("upperAge", giftInformation.getUpperAge())
					.addValue("lowerBudget", giftInformation.getLowerBudget()).addValue("upperBudget", giftInformation.getUpperBudget())
					.addValue("from", from).addValue("to", to);
		}else {
			sql = "select id, name, description, price_m, price_l, image_path, target_age, gender, deleted from items "
					+ "Where target_age Between :lowerAge And :upperAge And price_m Between :lowerBudget And :upperBudget And gender=:gender "
					+ "order by id"/* offset :from rows fetch next :to rows only;\"*/;
			
			param = new MapSqlParameterSource().addValue("lowerAge", giftInformation.getLowerAge()).addValue("upperAge", giftInformation.getUpperAge())
					.addValue("lowerBudget", giftInformation.getLowerBudget()).addValue("upperBudget", giftInformation.getUpperBudget()).addValue("gender", giftInformation.getGender())
					.addValue("from", from).addValue("to", to);
		}
		

		List<Item> itemList = template.query(sql, param, ROW_MAPPER);

		return itemList;
	}

	/**
	 * あいまい検索でアイテムを取得
	 * 
	 * @param name
	 * @return 商品リスト
	 */
	public List<Item> findByName(Integer from, Integer to, String name) {
		String sql = "select id, name, description, price_m, price_l, image_path, target_age, gender, deleted" + " from items"
				+ " where name ilike :name" + " order by id" /*+ " offset :from rows" + " fetch next :to rows only"*/;
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", '%' + name + '%').addValue("from", from)
				.addValue("to", to);

		List<Item> itemList = template.query(sql, param, ROW_MAPPER);

		return itemList;
	}

}
