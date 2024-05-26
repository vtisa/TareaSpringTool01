package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/verNumerosPrimos")
    public String verNumerosPrimos(@RequestParam int inicio, @RequestParam int fin, Model model) {
        List<Integer> numerosPrimos = obtenerNumerosPrimos(inicio, fin);
        model.addAttribute("numerosPrimos", numerosPrimos);
        return "resultados";
    }

    @GetMapping("/verTablaMultiplicar")
    public String verTablaMultiplicar(@RequestParam int numero, Model model) {
        List<ResultadoTabla> resultados = obtenerTablaMultiplicar(numero);
        model.addAttribute("resultados", resultados);
        model.addAttribute("numero", numero);  // Añadir el número a los atributos del modelo
        return "resultados2";
    }

    private List<Integer> obtenerNumerosPrimos(int inicio, int fin) {
        List<Integer> primos = new ArrayList<>();
        for (int i = inicio; i <= fin; i++) {
            if (esPrimo(i)) {
                primos.add(i);
            }
        }
        return primos;
    }

    private boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

    private List<ResultadoTabla> obtenerTablaMultiplicar(int numero) {
        List<ResultadoTabla> resultados = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            int resultado = numero * i;
            resultados.add(new ResultadoTabla(i, resultado, resultado % 2 == 0));
        }
        return resultados;
    }

    public static class ResultadoTabla {
        private int multiplicador;
        private int resultado;
        private boolean esPar;

        public ResultadoTabla(int multiplicador, int resultado, boolean esPar) {
            this.multiplicador = multiplicador;
            this.resultado = resultado;
            this.esPar = esPar;
        }

        // Getters y setters
        public int getMultiplicador() {
            return multiplicador;
        }

        public void setMultiplicador(int multiplicador) {
            this.multiplicador = multiplicador;
        }

        public int getResultado() {
            return resultado;
        }

        public void setResultado(int resultado) {
            this.resultado = resultado;
        }

        public boolean isEsPar() {
            return esPar;
        }

        public void setEsPar(boolean esPar) {
            this.esPar = esPar;
        }
    }
}
