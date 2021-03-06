import com.hazelcast.core.*;
import com.hazelcast.query.SqlPredicate;

public class ContinuousQueryMember {

    public static void main(String[] args) {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IMap<String, String> map = hz.getMap("map");
        map.addEntryListener(new MyEntryListener(),
                new SqlPredicate("name=peter"), true);
        System.out.println("EntryListener registered");
    }

    static class MyEntryListener
            implements EntryListener<String, String> {
        @Override
        public void entryAdded(EntryEvent<String, String> event) {
            System.out.println("entryAdded:" + event);
        }

        @Override
        public void entryRemoved(EntryEvent<String, String> event) {
            System.out.println("entryRemoved:" + event);
        }

        @Override
        public void entryUpdated(EntryEvent<String, String> event) {
            System.out.println("entryUpdated:" + event);
        }

        @Override
        public void entryEvicted(EntryEvent<String, String> event) {
            System.out.println("entryEvicted:" + event);
        }
    }
}
