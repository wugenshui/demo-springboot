package com.chenbo.demo.poi.tl.dbWord;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-04-05
 */
@Data
@Builder
public class TablePartData {

    private String title;

    private List<RowData> rows;
}
