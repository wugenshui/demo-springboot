package com.github.wugenshui.elasticsearch.bean;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.search.sort.SortOrder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderBean {

    private SortOrder orderSort;

    private String orderBy;

}
