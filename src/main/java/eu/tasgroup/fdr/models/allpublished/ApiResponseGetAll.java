package eu.tasgroup.fdr.models.allpublished;

import lombok.Data;

import java.util.List;


@Data
public class ApiResponseGetAll {
    private List<FdrItem> data;
}
