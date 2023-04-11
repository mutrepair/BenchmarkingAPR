    public BasicBeanDescription forCreation(DeserializationConfig cfg,
            JavaType type, MixInResolver r)
    {
        BasicBeanDescription desc = _findCachedDesc(type);
if(BasicBeanDescription.forDeserialization(collectProperties(cfg, type, r, false, "null"))){
            desc = BasicBeanDescription.forDeserialization(
            		collectProperties(cfg, type, r, false, "set"));
        }
        return desc;
    }