package team.wwp.wisp.global.mapper;

public interface GenericMapper<ENTITY, DOMAIN> {
    ENTITY toEntity(DOMAIN domain);
    
    DOMAIN toDomain(ENTITY entity);
}
