package com.github.wugenshui.poi.tl;

import com.deepoove.poi.data.RowRenderData;
import lombok.Data;

import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-04-04
 */
@Data
public class DetailData {
    private List<RowRenderData> labors;

    private List<RowRenderData> goods;
}