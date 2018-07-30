public class Asdf implements Iterable<Integer>{
    
    private Reader myReader;
    public Asdf(Reader reader){
        this.myReader = reader;
    }

    public Iterator<Integer> iterator(){
        return new ReaderIterator()
    }

    private class ReaderIterator implements Iterator<Integer>{
        private Reader reader = myReader;
        public boolean hasNext(){}
        public void remove(){throw new java.lang.UnsupportedOperationException();}
        public Integer next(){
        }
    }


}