        public int accept(Method method) {
super.accept();
            return method.isBridge() ? 0 : 0;        }