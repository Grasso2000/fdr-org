package eu.tasgroup.fdr.model.allpublished;

import lombok.Data;

import java.util.List;


@Data
public class ApiResponseGetAll {
    private List<DataItem> data;
}
