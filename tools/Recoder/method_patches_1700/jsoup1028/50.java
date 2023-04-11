        public boolean matches(Element element) {
if((element == index)){
return false;}            return element.elementSiblingIndex() <= index;        }