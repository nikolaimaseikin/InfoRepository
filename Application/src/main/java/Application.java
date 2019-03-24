import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class Application {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("E:\\Документ\\java_projects\\source\\Application\\src\\main\\resources\\adress.xml")); // Получаем файл, с которым будем работать.

        Node root = document.getDocumentElement(); //Получаю корневой элемент

        NodeList items = root.getChildNodes(); // Получаю дочерние узлы структуры

        List<Item> itemList = new ArrayList<Item>(); // Создаю список объектов класса Item
        List<Item> bufferList = new ArrayList<Item>(); // Создаю буффер для отсеивания уже обработанных записей



        //Заполняю itemList объектами

        for (int i = 0 ; i < items.getLength(); i++) {

            if(items.item(i).getNodeType() != Node.TEXT_NODE ) {
                itemList.add(getItem(items.item(i)));
            }

        }

        // Реализую  анализ повторяющихся записей
        Application.showEquals(itemList,bufferList);

        //Счётчик зданий
        Application.buildingCounter(bufferList);









    }

    // Метод формирования экземпляра Item из узла xml файла
    private static Item getItem(Node node) {
        Item item = new Item();

        // Если нода не текст, то это запись - заходим внутрь
        if(node.getNodeType() != Node.TEXT_NODE ) {
            // Получаем все атрибуты элемента и помещаем их в мапу
            NamedNodeMap attributes = node.getAttributes();

            // Присваиваю полям объекта значения соответствующих атрибутов
            item.setCity(attributes.item(0).getNodeValue());
            item.setFloor(attributes.item(1).getNodeValue());
            item.setHouse(attributes.item(2).getNodeValue());
            item.setStreet(attributes.item(3).getNodeValue());

        }


        return item;
    }

    private static void  printMessage(Item itm, int counter){

        System.out.println("Найдено совпадение в записи \n" + "City: "+itm.getCity() + "\n" + "Street: " + itm.getStreet() + "\n"+ "House: " + itm.getHouse() + "\n" + "Floor: " + itm.getFloor()+ "\n" + "Число совпадений:" +  counter + "\n" );

    }

    //Метод сравнения

    private static boolean equalsItems(Item a , Item b){
        boolean out = false;

        if(a.getCity().toString().equalsIgnoreCase(b.getCity().toString()) && a.getStreet().toString().equalsIgnoreCase(b.getStreet().toString()) && a.getHouse().toString().equalsIgnoreCase(b.getHouse().toString()) && a.getFloor().toString().equalsIgnoreCase(b.getFloor().toString())){

            out = true;

        }


        return out;
    }

    private static void showEquals(List<Item> itemList, List<Item> bufferList){

        int counter = 0;
        boolean proverka = true;

        System.out.println("======= Совпадений найдено ======= \n");
        for (int i = 0; i < itemList.size();i++){

            for(Item itm : bufferList){
                if(equalsItems(itemList.get(i),itm)){
                    proverka = false;
                }
            }

            if (proverka == true) {
                for (int j = 0; j < itemList.size(); j++) {

                    if (equalsItems(itemList.get(i), itemList.get(j)) && (i != j)) {

                        counter++;

                    }

                }

                // Выводить только дублирующиеся элементы
                if(counter>0) {
                    printMessage(itemList.get(i), counter);
                }

                counter = 0;
                bufferList.add(itemList.get(i));



            }


            proverka = true;




        }

        System.out.println("================================== \n");

    }

    private static void buildingCounter(List<Item> itemList){

       int[] counterArray = new int[5];
       List<String> cityNames = new ArrayList<String>();

        System.out.println("=========== Количество зданий в городах ========== \n");
       // Заполняем колекцию имён городов
       for (int i = 0; i < itemList.size(); i++){
           if(!cityNames.contains(itemList.get(i).getCity().toString())){
               cityNames.add(itemList.get(i).getCity().toString());
           }
       }

       for (int i = 0; i < cityNames.size();i++){
           for (int j = 0; j< itemList.size(); j++){
               if(itemList.get(j).getCity().toString().equalsIgnoreCase(cityNames.get(i))){
                   switch (Integer.parseInt(itemList.get(j).getFloor())){
                       case 1:
                           counterArray[0]++;
                           break;
                       case 2 :
                           counterArray[1]++;
                           break;
                       case 3 :
                           counterArray[2]++;
                           break;
                       case 4 :
                           counterArray[3]++;
                           break;
                       case 5 :
                           counterArray[4]++;
                           break;
                           default:
                               break;


                   }


               }
           }

           System.out.println("В городе " + cityNames.get(i) + "\n");
           for (int k = 0 ; k < counterArray.length; k++){
               System.out.println((k+1) + " этажных домов " + counterArray[k]);
               counterArray[k] = 0;
           }
           System.out.println("\n");

       }

        System.out.println("================================================== \n");

    }





}
