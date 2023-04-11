    public BasicBeanDescription forCreation(DeserializationConfig cfg,
            JavaType type, MixInResolver r)
    {
        BasicBeanDescription desc = _findCachedDesc(type);
if((type && (type == null))){
            desc = BasicBeanDescription.forDeserialization(
            		collectProperties(cfg, type, r, false, "set"));
        }
        return desc;
    }