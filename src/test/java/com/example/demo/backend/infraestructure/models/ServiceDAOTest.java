package com.example.demo.backend.infraestructure.models;

import com.handyman.backend.infraestructure.models.ServiceDAO;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class ServiceDAOTest {

    ServiceDAO serviceDAO;

    private TestInfo testInfo;
    private TestReporter testReporter;

    @BeforeEach
    public void initMetodoTest(TestInfo testInfo, TestReporter testReporter) {
        this.testInfo = testInfo;
        this.testReporter = testReporter;

        this.serviceDAO = new ServiceDAO(null, "123456", "12210258", "2022-08-13 05:37","2022-08-14 05:37");
        System.out.println("iniciamos el metodo");
        testReporter.publishEntry(" ejecutando: " + testInfo.getDisplayName() + " " + testInfo.getTestMethod().orElse(null).getName()
                + " con las etiquetas: " + testInfo.getTags());
    }

    @AfterEach
    public void finalizeMetodTest() {
        System.out.println("finalizando el metodo de prueba");
    }

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Inicializando el test");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Finalizando el test");
    }

    @Test
    @DisplayName("Fecha de Inicio y Fecha de Fin no nula")
    public void testFechasServicio() {
        assertNotNull(serviceDAO.getfInicio());
        assertEquals("2022-08-13 05:37", serviceDAO.getfInicio());
        assertEquals("2022-08-14 05:37", serviceDAO.getfFin());
    }

    @Nested
    @DisplayName("probando datos de servicio")
    public class ServicioTestIdTecnicoIdServicio {

        @Tag("Servicio")
        @Test
        @DisplayName("el nombre")
        public void testServicioId() {

            testReporter.publishEntry(testInfo.getTags().toString());
            if (testInfo.getTags().contains("Servicio")) {
                testReporter.publishEntry("Hacer algo con la etiqueta Servicio");
            }
            String esperado = "123456";
            String real = serviceDAO.getIdTecnico();
            assertNotNull(real, () -> "el Id Técnico no puede ser nula");
            assertEquals(esperado, real, () -> "el idTecnico no es el que se esperaba: se esperaba " + esperado
                    + " sin embargo fue " + real);
            assertTrue(real.equals("123456"), () -> "nombre cuenta esperado debe ser igual a la real");
        }
    }

}