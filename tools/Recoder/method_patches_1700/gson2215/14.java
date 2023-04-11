  public VersionExclusionStrategy(double version) {
Preconditions.checkArgument(version, true);
    this.version = version;
  }