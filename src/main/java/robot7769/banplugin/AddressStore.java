package robot7769.banplugin;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddressStore {

    private JavaPlugin plugin;

    private final List<String> blockedAddresses;

    private final List<int[]> blockedAddressesMin;
    private final List<int[]> blockedAddressesMax;

    public AddressStore(JavaPlugin plugin) {
        this.plugin = plugin;
        blockedAddresses = plugin.getConfig().getStringList("blocked-addresses");
        blockedAddressesMin = new ArrayList<>();
        blockedAddressesMax = new ArrayList<>();
        updateLimit();
    }
    public boolean isAddressBlocked(String address) {
        int[] a = aTi(address);
        for (int i = 0; i < blockedAddresses.size(); i++) {
            if (isBetween(a, blockedAddressesMin.get(i), blockedAddressesMax.get(i))) {
                return true;
            }
        }
        return false;
    }
    public boolean addBlockedAddress(String address) {
        if (!blockedAddresses.contains(address)) {
            blockedAddresses.add(address);
            save();
            updateLimit();
            return true;
        }
        return false;
    }
    public boolean removeBlockedAddress(String address) {
        if (blockedAddresses.remove(address)) {
            save();
            updateLimit();
            return true;
        }
        return false;
    }
    public void save() {
        plugin.getConfig().set("blocked-addresses", blockedAddresses);
        plugin.saveConfig();
    }

    public List<String> getBlockedAddresses() {
        return blockedAddresses;
    }

    private int[] addressToInteger(String address) {
        address = address.split("/")[0];
        int[] a = new int[4];
        for (int i = 0; i < 4; i++) {
            a[i] = Integer.valueOf(address.split("\\.")[i]);
        }
        return a;
    }

    private int[] aTi (String address) {
        return addressToInteger(address);
    }

    /**
     * Compare two addresses
     * @param a1
     * @param a2
     * @return  0 if address1 == address2
     * @return  1 if address1  > address2
     * @return -1 if address1  < address2
     */
    private int compereAddress(int[] a1, int[] a2) {
        for (int i = 0; i < 4; i++) {
            if (a1[i] > a2[i]) {
                return 1;
            } else if (a1[i] < a2[i]) {
                return -1;
            }
        }
        return 0;
    }

    private boolean isBetween(int[] address, int[] min, int[] max) {
        return compereAddress(address, min) >= 0 && compereAddress(address, max) <= 0;
    }

    private void updateLimit() {
        blockedAddressesMin.clear();
        blockedAddressesMax.clear();
        for (String address : blockedAddresses) {
            int prefix = Integer.valueOf(address.split("/")[1]);
            int[] min = aTi(address);
            int[] max = new int[4];
            System.arraycopy(min, 0, max, 0, 4);
            int mask = 32 - prefix;
            for (int i = 0; i < mask; i++) {
                int octetIndex = 3 - i / 8;
                int bitIndex = i % 8;
                max[octetIndex] |= 1 << bitIndex;
            }
            blockedAddressesMin.add(min);
            blockedAddressesMax.add(max);

            //! testing
            //System.out.println("address: " + address + " min: " + Arrays.toString(min) + " max: " + Arrays.toString(max));
        }
    }
}
