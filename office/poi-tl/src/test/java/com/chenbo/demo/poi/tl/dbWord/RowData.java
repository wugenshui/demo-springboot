package com.github.wugenshui.poi.tl.dbWord;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : chenbo
 * @date : 2020-04-05
 */
@Data
@AllArgsConstructor
public class RowData {
    private String name;

    private String type;

    private String key;

    private String defaultValue;

    private String remark;
}
