# Primer Parcial Distribuidos

##UDP

Sólo se pueden mandar **bytes**

Necesitamos

* _puerto_
* _sockets_ (cliente + servidor)

####Recibir (bloqueante)

```java
DatagramSocket socket = new DatagramSocket(serverPort); //asocia al puerto
byte[] buffer = new byte[1000];
DatagramPacket reply = new DatagramPAcket(buffer, buffer.length);
socket.receive(reply); // es bloqueante
new String(reply.getData(), 0, reply.length)
socket.close();
```

####Enviar

```java
DatagramSocket socket = new DatagramSocket();
InetAddress host = InetAddress.getByName("localhost");
byte[] message = m.getBytes(); // convierte el String a bytes
DatagramPacket request = new DatagramPacket(m, m.length, host, serverPort); // tengo que enviarlo al puerto que está escuchando
socket.send(request)
    
socket.close();
```

## TCP

Puedo mandar lo que sea (String = UTF)

Necesitamos

* _puerto_ donde está escuchando
* _IP_ del servidor

Si se mandan objetos la clase debe ser **Serializable**

```java
public class Person implements Serializable{
    //constructor + getters
}
```

### Servidor

##### Esperar clientes

```java
ServerSocket listenSocket = new ServerSocket(serverPort);
Socket clientSocket = listenSocket.accept(); //bloqueante (espera clientes)
Connection c = new Connection(clientSocket);
c.start(); //abre hilo por cada cliente
```

##### Clase Connection

Aquí se crea el canal con el cliente 

```java
class Connection extends Thread{
    DataInputSream in; //ObjectInputStream in;
    DataOutputStream out; //ObjectOutputStream out;
    Socket clientSocket;
}
```

### Crear el canal

```java
Socket s = new Socket("<IP>", serverPort);

//datos normales
DataInputStream in = new DataInputStream(s.getInputStream());
DataOutputStream out = new DataOutputStream(s.getOutputStream());

//Objetos (se deben inicializar primero out y luego in)
ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
ObjectInputStream in = new ObjectInputStream(s.getInputStream());
```

### Enviar mensajes

```java
out.writeUTF("msg"); // enviar Strings
out.writeInt(2); // enviar enteros

out.writeObject(obj); //si se mandan objetos
```

### Recibir mensajes (bloqueante)

```java
in.readUTF(); // leer Strings
in.readInt(); // lee enteros
in.readObject(); // lee objetos
```

##Multicast

Necesitamos

* _puerto cliente y servidor_  (no necesitan ser iguales)
* _IP multicast_ (224.0.0.0, 239.255.255.255)

### Enviar

```java
InetAddress group = InetAddress.getByName("<IP multicast>");
MulticastSocket s = new MulticastSocket(serverPort);
s.joinGroup(group);

String msg = "hola";
byte[] m = mesg.getBytes();

DatagramPacket messageOut = new DatagramPacker(m, m.length, group, clientPort);
s.send(messageOut);

s.leaveGroup(group);
s.close();
```

### Recibir

```java
InetAddress group = InetAddress.getByName("<IP multicast>");
MulticastSocket s = new MulticastSocket(clientPort);
s.joinGroup(group);

byte[] buffer = new byte[1000];
DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
s.receive(messageIn);

s.leaveGroup(group);
s.close();
```

## RMI

Se debe crear un .policy del servidor y del cliente

```java
grant  {
    permission java.security.AllPermission;
};
```

### Interfaz

Cada servicio debe tener su interfaz

```java
public interface Compute extends Remote{
    //metodos arrojan RemoteException
    public double square(int number) throws RemoteException;
}
```

### Servidor 

Es la clase que implementa el servicio (interfaz) y lo despliega

```java
System.setProperty("java.security.policy","file:/C:/Users/jgutierrgarc/Documents/NetBeansProjects/JavaRMI/src/server/server.policy");

if (System.getSecurityManager() == null) {
    System.setSecurityManager(new SecurityManager());
}
//start rmiregistry
LocateRegistry.createRegistry(1099);

ComputeServer engine = new ComputeServer(); //clase que se va a publicar
Compute stub = (Compute)UnicastRemoteObject.exportObject(engine, 0);

Registry registry = LocateRegistry.getRegistry();
registry.rebind("Compute", stub); // desplegar en RMI
```

### Cliente

Invoca el método

```java
System.setProperty("java.security.policy","file:/C:/Users/jgutierrgarc/Documents/NetBeansProjects/JavaRMI/src/server/server.policy");

if (System.getSecurityManager() == null) {
    System.setSecurityManager(new SecurityManager());
}

Registry registry = LocateRegistry.getRegistry("<IP del servidor RMI>");
Compute comp = (Compute) registry.lookup("Compute"); //busco el servicio en el rmiregistry
```

## POST/GET

para obtener parámetros

```java
request.getParameter("nombre");
```

## Servlets

Para escribir el código html en la página

```java
response.setContentType("text/html;charset=UTF-8");
PrintWriter out = response.getWriter();
...
out.close();
```

###Forward

```java
String next = "/pagina.html";
RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(next);
dispatcher.forward(request, response);
```

### Redirect

```java
response.sendRedirect("pagina.html")
```

### Include

Trabajar con varios servlets

```java
String next="/servlet2";
RequestDispatcher dispatcher = getServletContext().getRequestDispathcer(next);
dispatcher.include(request.response);
```

### Cookies

Añade cookies al objeto response

```java
// agregar cookies
Cookie ck = new Cookie("nombre", "valor");
response.addCookie(ck);

// borrar Cookies
ck.setMaxAge(0);

// obtener cookies
Cookie ck[] = request.getCookies();
ck[i].getName();
ck[i].getValue();
```

### Sesiones

```java
HttpSession mySession = request.getSession();

mySession.setAttribute("nombre", valor); // guardar valores
mySession.getAtrribute("nombre");
```

## Acceso base de datos

### Conexión a la bd

```java
Class.forName("org.apache.derby.jdbc.ClientDriver");

Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/myFirstDatabase", "root", "root"); //(bd, user, password)
```

### Consultas

```java
Statement query = con.createStatement();
ResultSet rs = query.executeQuery("<query>");

rs.next(); // itera sobre el resultado
rs.getInt("<nombre columna>");
rs.getString("<nombre_columna>");
    
con.commit(); // para modificar a la BD
```

### Modificaciones

```java
Statement query = con.createStatement();

query.executeUpdate("INSERT INTO CUSTOMERS VALUES()");
query.executeUpdate("UPDATE CUSTOMERS SET balance = balance WHERE id = id");
query.executeUpdate("DELETE FROM CUSTOMERS WHERE id = 3");
```

### ResultSet a arreglo

```java
Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
ResultSet rs = st.executeQuery("SELECT * FROM customers");

private Object[][] ResultSetToArray(ResultSet rs){
    Object data[][]=null; 
    try{
        rs. last();
        ResultSetMetaData rsmd = rs.getMetaData(); 
        int numCols = rsmd.getColumnCount();
        int numRows =rs.getRow();
        data=new Object[numRows][numCols];
        int j = 0;
        rs. beforeFirst();
        while (rs.next()){
        	for (int i=0;i<numCols;i++){ 
                data[j][i]=rs. getObject(i+1);
        	}
        	j++; 
        }
    }catch(Exception e){
    	System. out. println(e); 
    }
	return data; 
}
```

