package team.wwp.wisp.global.mapper;

public interface GenericMapper<ENTITY, DOMAIN> {
    ENTITY toENTITY(DOMAIN domain);
    
    DOMAIN toDOMAIN(ENTITY entity);
}
