        public boolean matches(Element element) {
if((index != null)){
return false;}            return element.elementSiblingIndex() >= index;        }