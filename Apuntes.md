# REST vs SOAP

###REST

* No hay estándares

* Elegimos el método para consultar el servicio web (GET, PUT, DELETE)

* Es mucho más rápico que con SOAP

* Sólo hay un método GET/PUT/… por cada servicio webMás rápido que el SOAP

* Por default se hace la operación GET, si quiero otro, en el _<form ..>_ se indica el métofo

  e.g. `<form action = "…" method = "POST">`

* La URI se pone manualmente

* Cuando haga un cliente desde una aplicación web se le debe de especificar todo (etiquetas)

* Header es obligatorio para habilitar POST, PUT, etc..

* En el _action_ de un form se pone el path al web service. E.j. action ="http://localhost:8080/RESTfulWebServices/webresources/MyPath"

#### Anotaciones

* _@path_: enviar parámetros/decir dónde está el servicio web
* _@produces_: qué produce el  método del  servicio (XML, JSON, …)
* _@consumes_: qué consume el servicio(texto plano, …)
* Cada método sólo puede consumir o producir

#### Parámetros

* Si los envío por POST necesito poner _@FormParam_ para que sepa dónde encontrarlos
* Si se envía por GET de necesita un _@QueryParam_
* Para utilizar _@PathParam_ hay que cambiar el _@Path_, y se hace por medio de GET

### SOAP

* Necesitamos mandar el _envelope_ (estándares SOAP) el cual implica enviar muchos más datos

### Laboratorio

* Convertidor de tipo de cambio --- Servicio web SOAP
  * moneda entrada
  * moneda salida
  * monto
  * __return__ monto convertido
* Se envuelve en un servicio web RESTful
  * genera cliente para consumir ws SOAP
  * __return__ salida del SOAP ws
* Navegador sólo se comunica con el ws RESTful
* real time
  * _onkeyup_ para que me de los resultados conforme ingreso el monto
  * _onchange_ para que actualize cuando cambie el ddl
  * utilizar GET

# Modelos de Comunicación Indirecta

Sender —> Servicios (_ultra robusto_) —> Receiver

* La parte de los servicios generalmente es más robusta
  * Se encarga de asegurarse de que los mensajes le lleguen al receiver
  * Si el receptor estaba caído cuando se manda el mensaje, puede que no lo reciba
    * El servidor se encarga de enviarle los mensajes en cuanto se levanta
* El servidor de servicios es el __cuello de botella__
  * Se puede ver como una nube con muchos servidores en eñña que se encargan de manejar los mensajes
  * Actúa como el intermediario
* Servidor normalmente está vinculado a una BD

Puede ser 1-1 / 1-muchos

Necesitamos que todos los programas estén acitvos __todo el tiempo__

* Hay que tomar en cuenta que a veces pueden fallar

* Lo que se pierde en latencia se recupera en flexibilidad

###Mecanismo orientado a procesadores

Todos los componentes ven la misma memoria 

Cuando mandas mensajes de dos lados distintos se pueden dar casos de sobreescritura

* Lee lo que otro acaba de escribir o viceversa

###Comunicación grupal

_esteroides del multicast_

Hay más control a nivel lógico

Sí garantiza que todos lo recibieron (_acknowledgment_)

* Mecanismos que garantizan la validez de los datos
* Verifican que el mensaje haya llegado bien (i.e. el mismo mensaje que se envió) (_integridad_)

Protocolos de consenso para verificar que todos hayan recibido el mismo mensaje

__Garantías:__ el mensaje que yo envié les llega a todos antes de que envíe el siguiente (llegan en orden)

* Se necesitan sistemas más robustos
* e.g. si recibe el (2) antes del (1) no lo procesa porque no le ha llegado el primero

Miembros pueden unirse/abandonar el grupo

* Los mensajes se envían a una vista del grupo
* Vista = # elementos que están recibiendo los mensajes
  * Así sé a quién le debe de llegar el mensaje (_fiabilidad_)
  * e.g. (b,c) están en la vista ahorita

Fallo sería cuando ya se lo envié otra vez pero aún así no le llegó

__Grupos abiertos vs. grupos cerrados:__  Se puede registrar quien sea vs. sólo los que ya están registrado 

####Ordenamiento de mensajes

* __Total__: Si ya envié un mensaje hay una entidad que retiene los siguientes mensajes hasta que todos los receptores hayan recibido bien el primero
* __FIFO:__ Con respecto a la fuente
  * Envié (1) y (2) —> sólo checo que estén en el mismo orden, es decir, que el (2) haya llegado después sin importar si llegó algo en medio.
  * Añade flexibilidad conservando algunas características de orden
  * Menos overhead 
* __Causal:__ a—>b, b—>d, …
  * No puedo ver el b si no he visto el a / no puedo ver el d si no he visto el b
  * Más flexible porque no me importa quién mandó el mensaje
  * Las reglas de correspondencia están en cada nodo

####Manejo de membresía

Si entro al grupo entonces sabe que también me tiene que enviar los mensajes a mí (actualiza la vista)

* Maneja cierre de sesión
* Sistema de monitoreo (si uno falla, actualiza la vista)

### Comunicación publicación - inscripción

Ya no hay cliente/servidor —> editor/subscriptor

e.g. Twitter: followers = subscriptor; el que tuitea = editor

A todos les llega el mensaje al mismo tiempo

Editores también pueden ser subscriptores y viceversa

* todos los mensajes los maneja un intermediario
* ya no hay roles definidos
* el intermediario debe ser robusto

Subscriptores buscan mensajes de un cierto tema

* checan por actualizaciones cada cierto intervalo de tiempo

__Propiedades:__

1. *Heterogeneidad*
2. *Asincronía*: Si fuera síncrona entonces el editor/subscriptor tendrían que estar "vivos" al mismo tiempo. La comunicación con el intermediario puede ser síncrona/asíncrona; sin embargo, la comunicación editor/subsciptor es asíncrona

__Operaciones:__

1. *Publicar* (eventos): Sólo editores 
2. *Subscribir* (filtro): Subscriptores; consumir eventos que se publiquen
3. _Anunciar_ (filtro): Editores avisan que van a transmitir un nuevo evento para buscar subscriptores.
4. _Notificar_ (evento)
5. _Cancelar subscripción_ (filtro)

Cada que un subscriptor recibe un mensaje envía un mensaje para confirmar que lo recibió (garantía de entrega)

#### Filtros

__Basado en canales__

Si te subscribes a un canal te llegan todos los eventos que se publiquen, sin importar el tema

e.g. HBO, 

__Basados en temas__

No importa quién lo publique o por cuál canal

Te llegan todos los eventos relacionados a ese tema

__Basados en contenido__

Condición sobre los atributos del evento

Se definen plantillas

e.g. Quiero del _agente 1_ y del _agente 2_ sólo quiero recibir los eventos de tipo _informativo_

__Basado en tipos__

Recibo todos los eventos que se puedan castear a un tipo en particular

#### Centralizado vs Distribuido

* Centralizado: Tengo un único intermediario y si se cae, se cae todo el sistema
* Distribuido: Tengo muchos editores y subscriptores publicando a diferentes canales
  * Se distribuye la carga, evitando el cuello de botella

#### Colas de Mensajes

Intermediario tiene una cola de mensajes persistente (está almacenado en algún tipo de BD)

* Tiene diferentes cola
* Los subscriptores se subscriben a una cola en particular

Los editores van publicando su contenido sin importar si alguien los recibe o no

Cada $x$ tiempo el subscriptor pregunta si hay un nuevo mensaje en la cola

Generalmente se utiliza para comunicación 1-1

* Puede haber muchos editores

* Siempre se asume un sólo subscriptor

Cada que se lee un mensaje se quita de la cola

* Si hubiera muchos subscriptores sólo uno (el primero) podría leer el mensaje nuevo

El mecanismo es asíncrono

* Recibir puede ser bloqueante/no bloqueante
* Se pueden implementar notificaciones cada que se reciba un mensaje

Tienen un orden __FIFO__

* Se le pueden asignar prioridades a los mensajes

Se pueden seleccionar mensajes por sus características

Mensajes son __persistentes__

Garantía de entrega pero no del _tiempo de entrega_ (cuándo lo entrego)

* No es una solución con overhead bajo

Se puede definir un límite de mensaje a las colas

Mensajes pueden ser parte de una __transacción__

* e.g. mensaje (1), (2) y (3) son parte de la misma transacción
* si no se consumen todos, se asume que no se consumión ninguno y los mensajes se quedan de manera persistente
* Los "paquetes" de mensajes se definen en los metadatos

Se puede transformar el mensaje dentro del servidor de aplicaciones

* e.g. yo quiero que los subscriptores lo lean en formato XML
* adaptamos el formato del mensaje a las capacidades de los subscriptores

Softwares: _Java Message Service_,  _WebSphere MQ_ (IBM), _MSMQ_ (Microsoft ), _Streams Advanced Queuing_ (Oracle)

#### Java Message Service

Comunicación 1-1

Mensajes sólo pueden ser consumidos 1 vez

No hay dependencias del tiempo

Subscriptor manda un _acknowledgment_

* Si no se manda la aplicación puede ser más rápida pero no tengo garantía de entrega

Cuando se 











