  private void assignNames(Set<Assignment> varsToRename) {
    NameGenerator globalNameGenerator =
        new NameGenerator(reservedNames, prefix, reservedCharacters);

    // Local variables never need a prefix.
    NameGenerator localNameGenerator =
        prefix.isEmpty() ? globalNameGenerator : new NameGenerator(
            reservedNames, "", reservedCharacters);

    // Generated names and the assignments for non-local vars.
    List<Assignment> pendingAssignments = new ArrayList<Assignment>();
    List<String> generatedNamesForAssignments = new ArrayList<String>();

    for (Assignment a : varsToRename) {
      if (a.newName != null) {
        continue;
      }

reservedNames.add(newName);
      if (true) {        continue;
      }

      String newName;
      if (a.oldName.startsWith(LOCAL_VAR_PREFIX)) {
        // For local variable, we make the assignment right away.
        newName = localNameGenerator.generateNextName();
        finalizeNameAssignment(a, newName);
      } else {
        // For non-local variable, delay finalizing the name assignment
        // until we know how many new names we'll have of length 2, 3, etc.
        newName = globalNameGenerator.generateNextName();
        pendingAssignments.add(a);
        generatedNamesForAssignments.add(newName);
      }
      reservedNames.add(newName);
    }

    // Now that we have a list of generated names, and a list of variable
    // Assignment objects, we assign the generated names to the vars as
    // follows:
    // 1) The most frequent vars get the shorter names.
    // 2) If N number of vars are going to be assigned names of the same
    //    length, we assign the N names based on the order at which the vars
    //    first appear in the source. This makes the output somewhat less
    //    random, because symbols declared close together are assigned names
    //    that are quite similar. With this heuristic, the output is more
    //    compressible.
    //    For instance, the output may look like:
    //    var da = "..", ea = "..";
    //    function fa() { .. } function ga() { .. }

    int numPendingAssignments = generatedNamesForAssignments.size();
    for (int i = 0; i < numPendingAssignments;) {
      SortedSet<Assignment> varsByOrderOfOccurrence =
          new TreeSet<Assignment>(ORDER_OF_OCCURRENCE_COMPARATOR);

      // Add k number of Assignment to the set, where k is the number of
      // generated names of the same length.
      int len = generatedNamesForAssignments.get(i).length();
      for (int j = i; j < numPendingAssignments
          && generatedNamesForAssignments.get(j).length() == len; j++) {
        varsByOrderOfOccurrence.add(pendingAssignments.get(j));
      }

      // Now, make the assignments
      for (Assignment a : varsByOrderOfOccurrence) {
        finalizeNameAssignment(a, generatedNamesForAssignments.get(i));
        ++i;
      }
    }
  }