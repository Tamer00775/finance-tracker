package kz.sdu.finance_tracker.mapper;

import kz.sdu.finance_tracker.dto.DebtorDto;
import kz.sdu.finance_tracker.entity.Debtor;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DebtorMapper {

    @Mapping(source = "user", target = "userDto")
    DebtorDto toDto(Debtor debtor);

    Debtor toEntity(DebtorDto debtorDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(DebtorDto dto, @MappingTarget Debtor entity);

}
