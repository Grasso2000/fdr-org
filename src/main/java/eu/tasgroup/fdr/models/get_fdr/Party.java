package eu.tasgroup.fdr.models.get_fdr;

import lombok.Data;

@Data
public class Party {
    private String type;
    private String id;
    private String pspId;
    private String pspName;
    private String pspBrokerId;
    private String channelId;
    private String organizationId;
    private String organizationName;
}
