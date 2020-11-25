package ${basePackage}.web;
import ${basePackage}.core.Result;
import ${basePackage}.core.ResultGenerator;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import ${basePackage}.core.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

/**
* Created by ${author} on ${date}.
*/
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping("/add")
    public Result add(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        Date date=new Date();
        ${modelNameLowerCamel}.setCreateTime(date);
        ${modelNameLowerCamel}.setUpdateTime(date);
        ${modelNameLowerCamel}.setIsDelete(false);
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        Result result=ResultGenerator.genSuccessResult();
        result.setData(${modelNameLowerCamel});
        return result;
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel}=new ${modelNameUpperCamel}();
        ${modelNameLowerCamel}.setId(id);
        ${modelNameLowerCamel}.setIsDelete(true);
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        Result result=ResultGenerator.genSuccessResult();
        result.setData(${modelNameLowerCamel});
        return result;
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Long id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        return ResultGenerator.genSuccessResult(${modelNameLowerCamel});
    }

    @PostMapping("/findAllByLike")
    public Result findAllByLike(@RequestBody Page<${modelNameUpperCamel}> page) {
    PageHelper.startPage(page.getPage(), page.getSize());
    List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findValidDeleteAll(page);
    PageInfo pageInfo = new PageInfo(list);
    return ResultGenerator.genSuccessResult(pageInfo);
    }
}
