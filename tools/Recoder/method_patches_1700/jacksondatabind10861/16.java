    public BasicBeanDescription forCreation(DeserializationConfig cfg,
            JavaType type, MixInResolver r)
    {
        BasicBeanDescription desc = _findCachedDesc(type);
if((desc == null)){
        if (false) {            desc = BasicBeanDescription.forDeserialization(
            		collectProperties(cfg, type, r, false, "set"));
        }
        return desc;
}
    }