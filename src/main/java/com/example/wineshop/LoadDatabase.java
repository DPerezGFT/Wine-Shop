package com.example.wineshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadDatabase {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(WineRepository wineRepository, WineryRepository wineryRepository, TypeRepository typeRepository, RegionRepository regionRepository) {

        return args -> {

            /*
            //BLOQUE DE COGIDO PARA CARGAR DATOS EN MARIADB

            RowCallbackHandler callback = new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet rs) throws SQLException {
                    typeRepository.save(new Type(rs.getString("type")));
                }
            };

            jdbcTemplate.query("select distinct type from wines_spa", callback);

            RowCallbackHandler callback2 = new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet rs) throws SQLException {
                    wineryRepository.save(new Winery(rs.getString("winery")));
                }
            };

            jdbcTemplate.query("select distinct winery from wines_spa", callback2);

            RowCallbackHandler callback3 = new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet rs) throws SQLException {
                    regionRepository.save(new Region(rs.getString("region"),
                            rs.getString("country")));
                }
            };

            jdbcTemplate.query("select distinct region, country from wines_spa", callback3);

            RowCallbackHandler callback4 = new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet rs) throws SQLException {
                    Winery winery = wineryRepository.findByName(rs.getString("winery"));
                    Type type = typeRepository.findByName(rs.getString("type"));
                    Region region = regionRepository.findByName(rs.getString("region"));
                    wineRepository.save(
                            new Wine(
                                    rs.getString("wine"),
                                    rs.getString("year"),
                                    rs.getDouble("rating"),
                                    rs.getInt("num_reviews"),
                                    rs.getDouble("price"),
                                    rs.getString("body"),
                                    rs.getString("acidity"),
                                    Math.toIntExact(winery.getId()),
                                    Math.toIntExact(type.getId()),
                                    Math.toIntExact(region.getId())
                            )
                    );
                }
            };

            jdbcTemplate.query("select * from wines_spa", callback4);*/

            Type aux1 = new Type("Brick");
            typeRepository.save(aux1);
            typeRepository.save(new Type("Espumoso"));

            Winery aux2 = new Winery("Hacendado");
            wineryRepository.save(aux2);
            wineryRepository.save(new Winery("BonArea"));

            Region aux3 = new Region("Malvarrosa", "Espa??a");
            regionRepository.save(aux3);
            regionRepository.save(new Region("Castelldefels", "Espa??a"));

            //wineRepository.save(new Wine("sdfhsfdh", "2020", 2, 2, 2, "si soy", "tambien soy", aux2, aux1, aux3 ));



            log.info("Preload Complete");

        };

    }
}
