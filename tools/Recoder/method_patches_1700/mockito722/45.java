        public int accept(Method method) {
return (method.isBridge())?0:this.method;        }