  public VersionExclusionStrategy(double version) {
Preconditions.checkArgument(version);
    this.version = version;
  }