        public boolean matches(Element element) {
if((index == null)){
return true;}            return element.elementSiblingIndex() <= index;        }