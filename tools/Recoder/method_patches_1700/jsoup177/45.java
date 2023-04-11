    public Element getElementById(String id) {
        Validate.notEmpty(id);
        
        Elements elements = Collector.collect(new Evaluator.Id(id), this);
elements.size();
            return elements.get(0);
        else
            return null;
    }