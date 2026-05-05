package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        List<ModelJson> entidades =JsonReader.read();

        //2.1 Tratamento dos dados
        List<ModelJson> entidadesValidas = new ArrayList<>();

        for(ModelJson e : entidades){
            if (e.getMinutes() > 0 ){
                entidadesValidas.add(e);
            }
        }
        int valoresignorados = entidades.size() - entidadesValidas.size();

        /// 2.2 Total por tarefa
        Map<Integer, Integer> totalPorTask = new HashMap<>();

        //Colocando valores de ModelJson no map totalPorTask
        for (ModelJson e : entidadesValidas) {
           totalPorTask.merge(e.getTaskId(), e.getMinutes(), Integer::sum);
        }


        List<TaskSummary> tasks = totalPorTask.entrySet().stream()
                .map(e -> new TaskSummary(e.getKey(), e.getValue()))
                .sorted((a, b) -> {
                    if (b.getTotalMinutes() != a.getTotalMinutes()) {
                        return b.getTotalMinutes() - a.getTotalMinutes();
                    }
                    return a.getTaskId() - b.getTaskId();
                })
                .toList();

        int totalGeral = tasks.stream()
                .mapToInt(TaskSummary::getTotalMinutes)
                .sum();


        List<TaskOutput> tasksOutput = tasks.stream()
                .map(t -> {

                    String taskName = entidadesValidas.stream()
                            .filter(e -> e.getTaskId() == t.getTaskId())
                            .map(ModelJson::getTaskName)
                            .findFirst()
                            .orElse("Desconhecida");

                    double perc = (t.getTotalMinutes() * 100.0) / totalGeral;

                    String percentage = String.format(Locale.US, "%.2f%%", perc);

                    return new TaskOutput(
                            t.getTaskId(),
                            taskName,
                            t.getTotalMinutes(),
                            percentage
                    );
                })
                .toList();


        ///2.3 Tarefa mais trabalhada
        //Pegando a task de primeira posição do Total por tarefa
        TaskOutput taskMaisTrabalahada = tasksOutput.get(0);

        ///2.4 Percentual por tarefa
        List<TaskPercentageOutput> taskPorcentagem = tasksOutput.stream()

                .map(t -> new TaskPercentageOutput(
                        t.getTaskId(),
                        t.getTaskName(),
                        t.getPercentage()
                ))
                .toList();
        ///2.5 Top 3 tarefas
        List<TaskPercentageOutput> top3TasksPercentual = tasksOutput.stream()
                .limit(3)
                .map(t -> new TaskPercentageOutput(
                        t.getTaskId(),
                        t.getTaskName(),
                        t.getPercentage()
                ))
                .toList();

        ///2.6 Ranking dos top 3 funcionários
        Map<Integer, Integer> top3usuarios = new HashMap<>();
        for(ModelJson e : entidadesValidas){
            top3usuarios.merge(e.getUserId(), e.getMinutes(), Integer::sum);
        }

        List<UserSummary> usuario = top3usuarios.entrySet().stream()
                .map(e -> {
                    int userId = e.getKey();
                    int totalMinuto = e.getValue();
                    String userName = entidadesValidas.stream()
                            .filter(x -> x.getUserId() == userId)
                            .map(ModelJson::getUserName)
                            .findFirst()
                            .orElse("DESCONHECIDO");
                    return new UserSummary(userId, userName, totalMinuto);
        })
                .sorted(
                        Comparator.comparing(UserSummary::getTotalMinutes).reversed()
                                .thenComparing(UserSummary::getUserId)
                )

                .toList();

        List<UserSummary> top3Employees = usuario.stream()
                .limit(3)
                .toList();


        ///2.7 Qual usuário trabalhou em mais tarefas distintas
        Map<Integer, Set<Integer>> tarefasPorUser = new HashMap<>();

        for(ModelJson e : entidadesValidas ){
            tarefasPorUser
                    .computeIfAbsent(e.getUserId(), k -> new HashSet<>())
                    .add(e.getTaskId());
        }

        List<UserTaskStats> stats = tarefasPorUser.entrySet().stream()
                .map( e -> {
                    int userId = e.getKey();
                    Set<Integer> taskId = e.getValue();
                    String userName = entidadesValidas.stream()
                            .filter(x -> x.getUserId() == userId)
                            .map(ModelJson::getUserName)
                            .findFirst()
                            .orElse("DESCONHECIDO");
                    return new UserTaskStats(userId, userName, taskId );
                })
                .sorted(
                        Comparator.comparing(UserTaskStats::getDistinctTasks).reversed()
                                .thenComparing(UserTaskStats::getUserId)
                )
                .toList();

        UserTaskStats mostDistinctUserOnTasks = stats.get(0);

        // RESULTADO
        Result result = new Result();

        result.setTotalMinutes(totalGeral);
        result.setTasks(tasksOutput);
        result.setMostWorkedTask(taskMaisTrabalahada);
        result.setTop3TasksPercentage(top3TasksPercentual);
        result.setTop3Employees(top3Employees);
        result.setMostDistinctUserOnTasks(mostDistinctUserOnTasks);
        result.setIgnoredRecords(valoresignorados);
        ObjectMapper mapper = new ObjectMapper();

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File("result.json"), result);

        System.out.println("result.json gerado com sucesso!");



    }}