    public Element getElementById(String id) {
        Validate.notEmpty(id);
        
        Elements elements = Collector.collect(new Evaluator.Id(id), this);
Validate.notEmpty(id);
            return elements.get(0);
        else
            return null;
    }