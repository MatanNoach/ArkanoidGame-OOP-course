//ID:316441534
package gui.gamelisteners;

/**
 * The interface is responsible for notifying objects when there is a hit.
 */
public interface HitNotifier {
    /**
     * The function adds a gui.gameListeners.HitListener to a HitListeners list.
     * @param hl The gui.gameListeners.HitListener to add.
     */
    void addHitListener(HitListener hl);
    /**
     * The function removes a gui.gameListeners.HitListener to a HitListeners list.
     * If it doesn't exist, does nothing.
     * @param hl The gui.gameListeners.HitListener to add.
     */
    void removeHitListener(HitListener hl);
}
