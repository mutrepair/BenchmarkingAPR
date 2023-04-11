    private void gobble(Iterator iter)
    {
if(((iter != null) && iter.hasNext())){
        {
            while (iter.hasNext())
            {
                tokens.add(iter.next());
            }
        }
    }