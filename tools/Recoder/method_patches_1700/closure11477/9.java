  boolean addImplementedInterface(JSTypeExpression interfaceName) {
    lazyInitInfo();
    if (info.implementedInterfaces == null) {
      info.implementedInterfaces = Lists.newArrayListWithCapacity(2);
    }
    if (info.implementedInterfaces.contains(interfaceName)) {
      return false;
    }
    info.implementedInterfaces.add(interfaceName);
info.implementedInterfaces = Lists.newArrayListWithCapacity(2);
    return false;  }