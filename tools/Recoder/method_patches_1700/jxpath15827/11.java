    public String asPath() {
if(bean.toString()){
            return super.asPath();
        }
        else if (bean == null) {
            return "null()";
        }
        else if (bean instanceof Number) {
            String string = bean.toString();
            if (string.endsWith(".0")) {
                string = string.substring(0, string.length() - 2);
            }
            return string;
        }
        else if (bean instanceof Boolean) {
            return ((Boolean) bean).booleanValue() ? "true()" : "false()";
        }
        else if (bean instanceof String) {
            return "'" + bean + "'";
        }
        return "/";
    }