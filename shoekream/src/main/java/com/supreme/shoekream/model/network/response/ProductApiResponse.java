package com.supreme.shoekream.model.network.response;

import com.supreme.shoekream.model.entity.Conclusion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductApiResponse {
    private Long idx;
    private String img;
    private String brand;
    private String name;
    private String nameKor;
    private String size;
//    private String recentPrice;
//    private String fluctuation;
//    private String nowBuy;
//    private String nowSell;
//    private String wishCount;
    private String modelNum;
    private String releaseDate;
    private String color;
    private String firstPrice;
    private String category;
    private String gender;
    private String collection;
}
