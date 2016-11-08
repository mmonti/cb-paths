package com.example;

import com.example.model.Path;
import com.example.repository.PathRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class CbPathsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CbPathsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(PathRepository pathRepository) {
		return (arg) -> {
			String[] paths = {
				"/kfp3/production/sq1/s1",
				"/kfp3/production/sq2/s1",
				"/kfp3/production/sq2/s2",
				"/mad5/production/sq1/s1",
				"/mad5/production/sq5/s3",
			};


			for (int i = 0; i < paths.length; i++) {
				String currentPath = paths[i];
				String[] segments = currentPath.split("/");

				List<Path> pathsToInsert = IntStream.range(0, segments.length).mapToObj(idx-> {

					String path = IntStream.range(0, idx)
							.mapToObj(x->segments[x])
							.filter(x->!x.isEmpty())
							.collect(Collectors.joining("/", "/", ""));
					String parent = idx > 1 ? segments[idx - 2] : "";

					String child = segments[idx];
					return new Path(path, parent, path, child);

				}).filter(x->!x.getChild().isEmpty()).collect(Collectors.toList());

				for (Path path : pathsToInsert) {
					try {
						pathRepository.save(pathsToInsert);
					} catch (Exception e) {
						System.out.println("Already exist");
					}
				}


			}

		};
	}
}
