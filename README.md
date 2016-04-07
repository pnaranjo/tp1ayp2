# tp1ayp2
TP1 - Algoritmos y Programacion 2 - UNTREF

Trabajo Práctico Final

Obligatorio

Primera Parte

Enunciado

Se quiere modelar un sistema de gestión para entidades bancarias. Los bancos gestionan

Cuentas bancarias.

Los clientes pueden abrir Cajas de Ahorro y Cuentas Corrientes y el Banco tiene Cuentas

Especiales para su funcionamiento.

Especificaciones:

Cuenta (para todos los tipos de Cuenta):

● Cada Cuenta tiene un número único que la identifica en todo el sistema bancario,

denominado CBU

● Tienen un saldo (dinero disponible).

● Tienen un histórico con todos los movimientos o transacciones realizadas.

● Se puede acreditar dinero. Es decir aumentar el saldo disponible en el monto

especificado.

● Se puede debitar dinero es decir restar del saldo un monto especificado.

● Pueden estar en estado activas (habilitadas) o inactivas. Las cuentas inactivas no

permiten realizar operaciones.

● Invariante de Cuenta: la suma de todos los movimientos es igual al saldo

actual

Caja de Ahorro (CA)

● Está asociada a uno o más Clientes (titulares de la cuenta) que deben ser Personas

Físicas. Una vez abierta una Caja de Ahorro no se pueden modificar sus titulares.

● No pueden tener saldo negativo.

● Pueden estar nominadas en pesos o dólares.

● Tienen un costo de mantenimiento mensual. Todas las Cajas de Ahorro tienen el

mismo costo de mantenimiento. Este costo es un monto fijo en la misma moneda que

la cuenta. Por lo tanto existirá un costo de mantenimiento para cuentas en pesos y

otro para cuentas en dólares.

● Para abrirla se debe depositar un monto de dinero.

● Una caja de ahorro permite depositar dinero, extraer dinero y transferir dinero a otra

cuenta.

● Cada caja de ahorro tiene una tasa de interés, que se pacta con cada cliente al

momento de la apertura.

Cuenta Corriente (CC)

● Está asociada a 1 o más clientes (titulares de la cuenta) que pueden ser personas

físicas o jurídicas. Una vez abierta la cuenta no se pueden modificar sus titulares.

● Al momento de abrirla se establece un acuerdo de sobregiro (monto en $) según la

situación crediticia de los titulares.

● Pueden tener saldo negativo hasta el monto acordado como sobregiro.

● Para abrirla se debe depositar un monto de dinero. El monto mínimo para abrir una

Cuenta Corriente es de $ 10.000.

● Una CC permite depositar dinero en efectivo y realizar transferencias a otra cuenta.

● Solo pueden estar nominadas en pesos.

● El BCRA (Banco Central de la República Argentina) fija una comisión, actualmente del

3%, que se cobra por cada movimiento que se realiza en una cuenta corriente (crédito,

transferencia o débito). Los montos de esas comisiones se depositan en una Cuenta

Especial del Banco denominada retenciones.

● No tienen costos de mantenimiento.

Cuentas Especiales (CE)

● Son cuentas propias del Banco que utiliza para su gestión. No están asociadas a

ningún cliente.

● Solo se usan para transferir dinero de las cuentas de los clientes al Banco.

● Se usan para cobrar comisiones y retenciones.

Clientes

Pueden ser clientes del banco, personas físicas o jurídicas que quedan unívocamente

identificados por su CUIT (Clave Única de Identificación Tributaria). Los datos mínimos que

se registran son los siguientes:

● Nombre o razón social

● CUIT

● Domicilio (dirección, código postal, localidad y provincia)

● Teléfono

Personas físicas

En el caso de personas físicas además se registra

● Tipo de Documento: DNI, Pasaporte, etc.

● Número de documento

● Estado civil

● Profesión

● Nombre y apellido del cónyuge

Personas jurídicas

En el caso de personas jurídicas además se registra:

● Fecha del contrato social

Transacciones

Las transacciones identifican cada movimiento que se realiza en un cuenta, cada transacción

se registra en el histórico de movimientos de la cuenta involucrada. Los datos registrados

son:

● Fecha y hora (obligatorio)

● Tipo de movimiento: crédito o débito (obligatorio)

● Monto (obligatorio)

● Motivo: por ejemplo, extracción por ventanilla, retención de impuestos, cobro de

comisión, etc. (obligatorio)

● Observaciones (optativo)

Operatoria Bancaria:

Se deberán implementar los módulos correspondientes para soportar las siguientes

operaciones bancarias:

● Operaciones por ventanilla

● Gestión de cuentas

● Gestión de clientes

● Procesos batch

Operaciones por ventanilla:

1. Depósito en efectivo:

Se denomina depósito en efectivo al acto de entregar al Banco una suma de dinero en

efectivo para acreditarlo a una Cuenta bancaria (sumarlo al saldo de la misma). La

operación se puede realizar si la cuenta destino está habilitada y solo permite

depósitos en la moneda nominal. Es decir en una cuenta en pesos solo se podrá

realizar depósitos en pesos y en una cuenta en dólares, solo depósitos en dólares.

2. Extracción en efectivo de Caja de Ahorro:

La extracción de dinero en efectivo de una Caja de Ahorro solo la puede realizar algún

titular de la cuenta. El cliente puede extraer el monto que desee hasta el saldo

disponible siempre y cuando la Caja de ahorro esté habilitada.

3. Extracción en efectivo de Cuenta Corriente:

Operación no permitida

4. Transferencia a otra Cuenta

Un titular de una Cuenta puede transferir dinero a cualquier Otra Cuenta. Si la

operación es exitosa se debita de la cuenta origen el monto especificado y se acredita

en la cuenta destino. Es exitosa solo si la cuentas origen y destino están habilitadas y

si la cuenta origen tiene saldo suficiente, incluyendo los impuestos que puedan

corresponder.

La cuenta destino puede estar nominada en la misma moneda que la de origen o en

otra moneda. En el caso de transferencia a una cuenta en otra moneda distinta a la

moneda de la cuenta origen, se realiza la conversión al tipo de cambio vigente al

momento de la operación. En el caso de conversión de monedas se debe registrar en el

campo observación de la transacción la moneda de la operación, el monto en esa

moneda y el tipo de cambio vigente.

5. Listar movimientos de una cuenta

Se debe poder listar por pantalla todos los movimientos o los últimos n movimientos

especificados.

Gestión de Cuentas:

1. Apertura de Caja de Ahorro:

Uno o varios clientes activos pueden abrir una Caja de Ahorro realizando un depósito

inicial. Al momento de la apertura se fija el costo de mantenimiento que puede ser 0

2. Apertura de Cuenta Corriente:

Uno o varios clientes activos pueden abrir una Cuenta Corriente realizando un depósito

inicial.

3. Inhabilitar Cuenta

Pasar una Cuenta a estado inactivo para impedir que se realicen movimientos.

4. Habilitar Cuenta

Habilitar nuevamente una Cuenta

Gestión de clientes:

1. Alta de cliente:

Se pueden dar de alta nuevos clientes que no se encuentren registrados previamente.

La clave única que permite identificar a un cliente es el CUIT.

2. Baja de cliente:

Se pueden dar baja a clientes solo si no tienen asociada una Cuenta activa, en ese

caso el cliente pasa a estado inactivo

3. Buscar un cliente

Se puede buscar un cliente por su número de CUIT, nombre y apellido o razón social o

número de documento. Se debe mostrar por pantalla todos los datos registrados del

cliente y todas las cuentas de la cual es titular.

4. Activar un cliente:

Se puede activar a un cliente registrado que se dió de baja anteriormente.

Procesos Batch:

1. Cobro de mantenimientos:

Cuando se ejecuta este proceso se debitan de todas las cuentas los costos de

mantenimiento correspondientes y se acreditan en la cuenta especial del banco

llamada mantenimientos. Si se trata de costos en otra moneda que no sea peso, se

convierte al tipo de cambio vigente. Como resultado del proceso se generan dos

archivos de texto denominados:

● MantenimientosCobrados<aaaa­mm­dd>

● ErroresMantenimiento<aaaa­mm­dd>

En MantenimientoCobrados se registran cada una de los cobros percibidos y

acreditados en la cuenta mantenimieto con el siguiente formato

Cada línea es un registro de valores separados por comas de los siguientes campos:

CBU,Tipo de cuenta: CA, monto, moneda, tipo_de_cambio

En ErroresMantenimiento se registran cada una de las cobros que no se pudieron

cobrar con el siguiente formato:

CBU,Tipo de cuenta: CA, monto, motivo: cuenta inhabilitada, saldo insuficiente, etc.

Consideraciones

● Todas las entidades deben implementar el método toString() para poder mostrarse

adecuadamente, e implementar la interfaz comparable

● Se deben utilizar excepciones para garantizar que se cumplen los contratos

(pre­condiciones, post­condiciones e invariantes)

●

Forma y fechas de entrega y aprobación

● Diagrama de clases y pruebas unitarias:7/4 para Ma y Jue, 8/4 para Mie y Vie.

El diagrama de clases debe ser entregado en forma impresa, puede ser construido

en herramienta de diseño, o realizado con lápiz y papel.

● Fecha de entrega del proyecto completo: 28/4 para Ma y Jue, 29/4 para Mie y Vie.

● Para la aprobación del trabajo práctico obligatorio se deberá tener aprobada tanto la

entrega del TP como la defensa del mismo.

El trabajo deberá realizarse en grupos de hasta 4 (cuatro) alumnos.

Se deberá entregar en formato impreso y digital:​

● Informe

● Archivo comprimido con las clases de java (sólo los archivos .java).

● Proyecto java (.project), y todos los archivos necesarios para importar el proyecto sin

inconvenientes. Zippear la carpeta del proyecto

El informe debe incluir:

● Nombres de los integrantes del grupo.

● Diagrama de clases.

● Decisiones de diseño tomadas.

● Lista de archivos .java comprendidos en solución del problema.
