package eu.tasgroup.fdr.models.published;

import lombok.Data;

@Data
public class Sender {

    private String type;

    private String id;

    private String pspName;

    private String pspBrokerId;

    private String channelId;

    private String password;

    private String pspId;
}
