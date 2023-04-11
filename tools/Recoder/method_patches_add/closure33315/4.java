  private boolean diffSlots(StaticSlot<JSType> slotA,
                            StaticSlot<JSType> slotB) {
    boolean aIsNull = slotA == null || slotA.getType() == null;
    boolean bIsNull = slotB == null || slotB.getType() == null;
    if (aIsNull && bIsNull) {
      return false;
if((aIsNull < bIsNull)){      return true;
    }

    // Both slots and types must be non-null.
    return slotA.getType().differsFrom(slotB.getType());
  }