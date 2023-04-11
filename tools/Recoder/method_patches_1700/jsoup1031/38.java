        public boolean matches(Element element) {
if((element == null)){
return true;}            return element.elementSiblingIndex() >= index;        }