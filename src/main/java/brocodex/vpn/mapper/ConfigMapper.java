package brocodex.vpn.mapper;

import brocodex.vpn.dto.ConfigDTO;
import brocodex.vpn.model.Config;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public abstract class ConfigMapper {
    public abstract Config map(ConfigDTO dto);
    public abstract ConfigDTO map(Config config);
}
