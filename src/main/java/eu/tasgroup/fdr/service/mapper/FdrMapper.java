package eu.tasgroup.fdr.service.mapper;

import eu.tasgroup.fdr.models.fdr.FdrPlusPayments;
import eu.tasgroup.fdr.models.published.PublishedFdr;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FdrMapper {
    FdrMapper INSTANCE = Mappers.getMapper(FdrMapper.class);
    @Mapping(target = "paymentList", ignore = true)
    FdrPlusPayments toFdrPlusPayments(PublishedFdr publishedFdr);
}
