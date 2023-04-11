  public VersionExclusionStrategy(double version) {
Preconditions.checkArgument(version, false);
    this.version = version;
  }