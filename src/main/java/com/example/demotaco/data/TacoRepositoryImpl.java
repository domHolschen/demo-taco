package com.example.demotaco.data;

import com.example.demotaco.domain.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Objects;

@Repository
public class TacoRepositoryImpl implements TacoRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public TacoRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for(String ingredientId:taco.getIngredients()) {
            saveIngredientToTaco(ingredientId, tacoId);
        }
        return taco;
    }

    private long saveTacoInfo(Taco taco) {
        //PreparedStatementCreatorFactory pcsf = new PreparedStatementCreatorFactory("insert into Taco (name, createdAt) values(?,?)", Types.VARCHAR, Types.TIMESTAMP);
        PreparedStatementCreatorFactory pcsf = new PreparedStatementCreatorFactory("insert into Taco (name, createdAt) values(?,?)", Types.VARCHAR, Types.TIMESTAMP);
        pcsf.setReturnGeneratedKeys(Boolean.TRUE);

        PreparedStatementCreator psc = pcsf.newPreparedStatementCreator(Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedAt().getTime())));

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(psc, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey().longValue());
    }

    private void saveIngredientToTaco(String ingredientId, long tacoId) {
        jdbc.update("insert into Taco_Ingredients (taco, ingredient) values(?,?)", tacoId, ingredientId);
    }
}